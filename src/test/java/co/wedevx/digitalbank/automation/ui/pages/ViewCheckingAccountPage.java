package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewCheckingAccountPage extends BaseMenuPage{

    // Constructor to initialize the WebDriver and PageFactory
    public ViewCheckingAccountPage(WebDriver driver){
       super(driver);
    }

    @FindBy(id = "new-account-conf-alert")
    private WebElement newAccountConfirmation;


    @FindBy(xpath = "//div[@id='firstRow']/div")
    private List<WebElement> allFirstRowDivs;


    @FindBy(xpath = "//table[@id='transactionTable']/tbody/tr")
    private WebElement firstRowOfTransactions;




    public Map<String,String> getNewlyAddedCheckingAccountTransactionInfoMap(){
        // //table[@id='transactionTable']/tbody/tr/td
        List<WebElement> firstRowColumns = firstRowOfTransactions.findElements(By.xpath("./td"));


//        double actualAmount=Double.parseDouble(firstRowColumns.get(3).getText().substring(1));//not include $
//        double actualBalance=Double.parseDouble(firstRowColumns.get(4).getText().substring(1));
        Map<String,String> actualResultMap =new HashMap<>();
        actualResultMap.put("actualCategory",firstRowColumns.get(1).getText());
        actualResultMap.put("actualDescription",firstRowColumns.get(2).getText());
        actualResultMap.put("actualAmount",firstRowColumns.get(3).getText().substring(1));
        actualResultMap.put("actualBalance",firstRowColumns.get(4).getText().substring(1));

        return actualResultMap;


    }



    public Map<String,String> getNewlyAddedCheckingAccountInfoMap(){
      WebElement lastAccountCard=allFirstRowDivs.get(allFirstRowDivs.size()-1);
      String actualResult=lastAccountCard.getText();

      Map<String,String> actualResultMap=new HashMap<>();
      actualResultMap.put("actualAccountName",actualResult.substring(0,actualResult.indexOf("\n")).trim());
      actualResultMap.put("actualAccountType",actualResult.substring(actualResult.indexOf("Account"),actualResult.indexOf("Ownership")).trim());
      actualResultMap.put("actualOwnership",actualResult.substring(actualResult.indexOf("Ownership:"),actualResult.indexOf("Account Number:")).trim());
      actualResultMap.put("actualAccountNumber",actualResult.substring(actualResult.indexOf("Account Number:"),actualResult.indexOf("Interest Rate")).trim());
      actualResultMap.put("actualInterestRate",actualResult.substring(actualResult.indexOf("Interest Rate:"),actualResult.indexOf("Balance:")).trim());
      actualResultMap.put("actualBalance",actualResult.substring(actualResult.indexOf("Balance:")).trim());


      return actualResultMap;

      //  Elon Musk Second Checking
//        Account: Standard Checking
//        Ownership: Individual
//        Account Number: 486133131
//        Interest Rate: 0.0%
//
//        Balance: $1000.00




  }

  public String getActualCreateAccountConfirmationMessage(){
        return newAccountConfirmation.getText();
    }

}
