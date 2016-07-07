from selenium import webdriver
from selenium.webdriver.common.keys import Keys

driver = webdriver.Firefox()
driver.get("https://libraries.io")
assert "Libraries" in driver.title
elem = driver.find_element_by_name("q")
elem.clear()
elem.send_keys("selenium python")
elem.send_keys(Keys.RETURN)
driver.find_element_by_link_text("selenium").click()
