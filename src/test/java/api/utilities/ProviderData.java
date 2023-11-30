package api.utilities;

import org.testng.annotations.DataProvider;

public class ProviderData {
    @DataProvider(name="petData")
    public String [][] petDataFromExcel(){
        String path = System.getProperty("user.dir") + "\\testdata\\petData.xlsx";
        XLUtility petDataUtility = new XLUtility(path);
        

        int rowNumber = petDataUtility.getRowCount("Sheet1");
        int columnNumber = petDataUtility.getCellCount("Sheet1", rowNumber);
        //System.out.println("Rows : "+rowNumber+ " Columns : " + columnNumber);

        String apiData [][] = new String[rowNumber][columnNumber];
        for(int i = 1; i<rowNumber+1; i++ ){
            for(int j = 0; j<columnNumber; j++){
                apiData[i-1][j] = petDataUtility.getCellData("Sheet1", rowNumber, columnNumber);
                //System.out.println(apiData[i-1][j]); 
            }
        }
        return apiData;
    }
}
