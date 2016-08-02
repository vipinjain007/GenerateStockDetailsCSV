package com.stock.details;


import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
public class StockInformationGenerator {
	
	protected Logger logger = (Logger) LoggerFactory.getLogger(getClass());

	@Autowired
	private StockService stockService;

	@Scheduled(cron = "0 0/1 * * * ?")
	public void generateStockDetails() throws StockException {
	
		logger.info("generateStockDetails job...");
		
		try {
			stockService.generateStockDetails();
			
		} catch (Exception e) {
			logger.error("error running update redis data job ",e);
			throw new StockException(e);
		}

	}

	
}
