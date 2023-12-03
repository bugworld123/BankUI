package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;
import java.util.NoSuchElementException;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCheckingPage extends BaseMenuPage {


    public CreateCheckingPage(WebDriver driver){
        super(driver);
    }



    @FindBy(id = "Standard Checking")
    private  WebElement standardCheckingAccountTypeRadioButton;

    @FindBy(id = "Interest Checking")
    private  WebElement interestCheckingAccountTypeRadioButton;

    @FindBy(id = "Individual")
    private  WebElement individualOwnerShipTypeRadioButton;

    @FindBy(id = "Joint")
    private  WebElement jointOwnerShipTypeRadioButton;

    @FindBy(id = "name")
    private  WebElement accountNameTxt;

    @FindBy(id = "openingBalance")
    private  WebElement openingBalanceTxtBox;

    @FindBy(id = "newCheckingSubmit")
    private  WebElement submitButton;






    //assertEquals(expectedConfMessage,newAccountConfirmation.getText().substring(0,newAccountConfirmation.getText().indexOf("\n")));

    public void createNewChecking(List<NewCheckingAccountInfo> checkingAccountInfoList){
        NewCheckingAccountInfo testDataForOneCheckingAccount=checkingAccountInfoList.get(0);
        // user clicks on checking button
        checkingMenu.click();

        // the user clicks on the new checking button
        newCheckingButton.click();
        assertEquals(ConfigReader.getPropertyValue("digitalbank.createnewcheckingurl"),getDriver().getCurrentUrl(),"new Checking Button didn't take the url");

        // the user selects a accountType

       if(testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Standard Checking")){
           standardCheckingAccountTypeRadioButton.click();
       }else if(testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Interest Checking")){
           interestCheckingAccountTypeRadioButton.click();
        }else{
          throw new  NoSuchElementException("Invalid checking account type option provided. Only supports Standard Checking and Interest Checking ");
       }



        if(testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Individual")){
            individualOwnerShipTypeRadioButton.click();
        } else if(testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Joint")){
            jointOwnerShipTypeRadioButton.click();
        }else{
            throw new  NoSuchElementException("Invalid ownership type option");
        }


        accountNameTxt.sendKeys(testDataForOneCheckingAccount.getAccountName());
        openingBalanceTxtBox.sendKeys(String.valueOf(testDataForOneCheckingAccount.getInitialDepositAmount()));
        submitButton.click();
    }









}
