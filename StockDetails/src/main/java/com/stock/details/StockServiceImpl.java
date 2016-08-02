package com.stock.details;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.support.collections.DefaultRedisMap;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.stock.details.domain.*;



@Service
public class StockServiceImpl implements StockService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	public static String StockCodeFilePath = "src/main/resources/Stock.txt";
	public static String yahooFinceApi = "https://www.yahooapis.com/api/v1/Stockfo?StockCode=";
	public static String csvFileName="StockDetails";
	
	@Autowired
	private DefaultRedisMap<String, String> cacheMap;
	
	public void generateStockDetails() throws StockException {

		Path path = null;
		Map<String, String> stockCodeMap = null;
		List<stockDetails>  stockDetailsList=new ArrayList<stockDetails>();

		try {
			path = Paths.get(StockCodeFilePath);

			stockCodeMap = createMapUsingFile(path);
			FileWriter file=CSVUtility.createFileHeader(csvFileName);
			stockCodeMap.keySet().forEach(stockcode -> {

				RestTemplate restTemplate = new RestTemplate();
				LinkedHashMap<String, String> result;
			    
			  	
			  
				result = restTemplate.getForObject(yahooFinceApi + stockcode, LinkedHashMap.class);
				if (result==null){
					stockDetails stockDetails=new stockDetails();
					stockDetails.setStockSymbol("-1");
					
					
				}
				else{
					stockDetails stockDetails=new stockDetails();
					stockDetails.setStockSymbol(result.get("stockSymbol"));
					stockDetails.setCurrentPrice(result.get("currentPrice"));
					stockDetails.setYearTargetPrice(result.get("yearTargetPrice"));
					stockDetails.setYearHigh(result.get("yearHigh"));
					stockDetails.setYearLow(result.get("yearLow"));
				}

			});
			CSVUtility.writeCsvFile(file,stockDetailsList);
			

		} catch (StockException e) {

			logger.error("Exception in API", e);
			throw new StockException("Stock Details Error due to yahoo service down");
		}
	}

	private Map<String, String> createMapUsingFile(Path path) throws StockException {
		Map<String, String> StockMap = new HashMap<String, String>();
		try {
			try (Stream<String> lines = Files.lines(path)) {
				lines.forEachOrdered(line -> {

					StockMap.put(line.trim(), line.trim());
				});
				return StockMap;
			}
		} catch (Exception e) {
			throw new StockException(e);
		}
	}

	

}
