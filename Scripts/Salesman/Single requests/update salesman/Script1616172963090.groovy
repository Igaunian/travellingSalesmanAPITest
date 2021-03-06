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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.testobject.RequestObject

def updateBody = """{
    "id": "${GlobalVariable.salesman_id}",
    "username": "smctear2",
    "email": "smctear2@sitemeter.com",
    "password": "password",
    "lastname": "McTear",
    "middleName": "Cristal",
    "firstname": "Updated",
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
    "drivingLicence": true,
    "roles": [
        "ROLE_SALESMAN"
    ],
    "matchLevel": "PERFECT"
}"""

def updateSalesman = (RequestObject)findTestObject('TS/put', [('base_url') : GlobalVariable.base_url, ('endpoint') : 'salesman/update-profile', ('token') : GlobalVariable.salesman_token]);

def updateBodyContent = new HttpTextBodyContent(updateBody, 'UTF-8', 'application/json')
println(updateBodyContent.getText())

try {
	updateSalesman.setBodyContent(updateBodyContent)
}
catch(Exception e) {
	println(e.printStackTrace())
};


def response = WS.sendRequest(updateSalesman)

println(response)

def jsonSlurper = new JsonSlurper()
def result = jsonSlurper.parseText(response.getResponseBodyContent())

println(result)

WS.verifyResponseStatusCode(response, 200)

assert result.firstname == 'Updated'

