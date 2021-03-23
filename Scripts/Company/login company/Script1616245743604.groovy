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
import com.kms.katalon.core.testobject.TestObjectProperty

String loginBody = """{
	"username" : "SC",
	"password" : "password"
}""";

def login = (RequestObject)findTestObject('TS/post no auth', [('base_url') : GlobalVariable.base_url, ('endpoint') : 'auth/company-login']);

try {
	login.setBodyContent(new HttpTextBodyContent(loginBody, 'UTF-8', 'application/json'))
}
catch(Exception e) {
	println(e.printStackTrace())
};

def response = WS.sendRequest(login)

def jsonSlurper = new JsonSlurper()
def result = jsonSlurper.parseText(response.getResponseBodyContent())

GlobalVariable.company_token = result.token
GlobalVariable.company_id = result.userId

WS.verifyResponseStatusCode(response, 200)

assert 'SC' == result.username


