import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper

String registrationBody = '''{
        "registrationType": "salesmanRegistration",
        "username": "smctear2",
        "email": "smctear2@sitemeter.com",
        "password": "password",
        "lastname": "McTear",
        "middleName": "Cristal",
        "firstname": "Sasha",
        "phoneNumber": 276599855,
        "nationality": "Austria",
        "country": "Russia",
        "county": "Schäffern",
        "city": "Kil’mez’",
        "postcode": 36984,
        "address": "Crownhardt",
        "houseNumber": "1",
        "birthDate": "1971-10-25",
        "gender": "male",
        "drivingLicence": true
}''';

def registrate = (RequestObject)findTestObject('TS/post no auth', [('base_url') : GlobalVariable.base_url, ('endpoint') : 'registration/']);

try {
	registrate.setBodyContent(new HttpTextBodyContent(registrationBody, 'UTF-8', 'application/json'))
}
catch(Exception e) {
	println(e.detailMessage)
};

def response = WS.sendRequest(registrate);
WS.verifyResponseStatusCode(response, 200)



