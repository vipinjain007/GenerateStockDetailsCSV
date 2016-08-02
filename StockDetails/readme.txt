Requirement understanding:
[1]Read stock.txt file and got stockCode
[2]Pass this stockcode to yahoo service this service will return stocksymbol,currentprice,yeartargetprice ,yearhigh ,yearlow
[3]In this step i need to put this service response in csv file if service return null then in csv file need to put -1 value for that stock code
[4]For improve the performance I have  configure redis (Not completed due to time limit)

 
Implementation Concept:
[1]For better architecture purpose  I have created Springboot Application.
[2]This springboot Application i have write  scheduled in StockInformationGenerator.java class (which is running every 1 min)
[3]This class is calling  generateStockDetails method from StockServiceImpl class  .
[4]This method is take care all logic
[A] First this method read stock.txt file and creat stockCodeMap<StockCode ,StockCode>
[B]Then We Iterate stockCodeMap   and pass to yahooservice (This is given result in LinkedHashMap)
[C]if service return result then it will crate stockDetails object and put into List<stockDetails>.
[4]Once  List<stockDetails>. is ready we are passing CSVUtility class which will create csv for us.


For caching purpose i have integrated redis 

Need more logic in redis we are going to create map with key-> time and key-Details
if time is just 60 second or leass then greater then then we donr need to call yahoo service insted of we get details from redis map using details key else we need to get from yahoo service and update redis map.