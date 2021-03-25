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

def deleteBody = """{
    "id": "${GlobalVariable.salesman_id}",
    "username": "Imojean",
    "password": "password",
    "email": "Dorene.Shaddock@gmail.com",
    "firstname": "Dorene",
    "lastname": "Shaddock",
    "middleName": "Sadowski",
    "gender": "male",
    "nationality": "Canadian",
    "country": "Nauru",
    "county": "Toronto",
    "city": "Saskatoon",
    "address": "Paul Johnson street",
    "postcode": 71643,
    "houseNumber": 21,
    "phoneNumber": 301234567,
    "birthDate": "1987-06-18",
    "drivingLicense": false,
    "roles": [
        "ROLE_USER"
    ],
    "matchLevel": null
}"""

def deleteSalesman = (RequestObject)findTestObject('TS/delete', [('base_url') : GlobalVariable.base_url, ('endpoint') : 'salesman/delete-profile', ('token') : GlobalVariable.salesman_token]);

//List parameters = new ArrayList<>()
//parameters.add(new TestObjectProperty(‘salesman_id’, ConditionType.EQUALS, GlobalVariable.salesman_id))
////list.add(parameters)
//request.setRestParameters(parameters)
//println(parameters)

def deleteBodyContent = new HttpTextBodyContent(deleteBody, 'UTF-8', 'application/json')
println(deleteBodyContent.getText())

try {
	deleteSalesman.setBodyContent(deleteBodyContent)
}
catch(Exception e) {
	println(e.printStackTrace())
};


def response = WS.sendRequest(deleteSalesman)

println(response)

WS.verifyResponseStatusCode(response, 200)


