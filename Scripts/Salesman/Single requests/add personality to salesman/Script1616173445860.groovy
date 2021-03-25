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


def personalityBody = """{
    "salesmanId": "${GlobalVariable.salesman_id}",
    "studyIndex" : 6,
    "vocabulary" : 6,
    "readingLiteracy" :7,
    "calculation" : 6,
    "numberComprehension" : 7,
    "energyLevel" : 6,
    "assertiveness" : 7,
    "socialContacts" : 5,
    "compliance" : 6,
    "attitude" : 7,
    "decisionMaking" : 5,
    "adaptability" : 5,
    "independence" : 4,
    "objectiveDecisionMaking" : 5,
    "entrepreneurship" : 1,
    "creativity" : 2,
    "humanityFocus" : 3, 
    "mechanical" : 4,
    "scientificProfessional" : 5, 
    "administrative" : 6
}"""

def addPersonality = (RequestObject)findTestObject('TS/post', [('base_url') : GlobalVariable.base_url, ('endpoint') : 'personality/add-personality-to-salesman', ('token') : GlobalVariable.salesman_token]);

//List parameters = new ArrayList<>()
//parameters.add(new TestObjectProperty(‘salesman_id’, ConditionType.EQUALS, GlobalVariable.salesman_id))
////list.add(parameters)
//request.setRestParameters(parameters)
//println(parameters)

def addPersonalityBodyContent = new HttpTextBodyContent(personalityBody, 'UTF-8', 'application/json')
println(addPersonalityBodyContent.getText())

try {
	addPersonality.setBodyContent(addPersonalityBodyContent)
}
catch(Exception e) {
	println(e.printStackTrace())
};


def response = WS.sendRequest(addPersonality)

def jsonSlurper = new JsonSlurper()

def result = jsonSlurper.parseText(response.getResponseBodyContent())

WS.verifyResponseStatusCode(response, 200)

assert result.studyIndex == 6
assert result.salesman.username == 'smctear2'




