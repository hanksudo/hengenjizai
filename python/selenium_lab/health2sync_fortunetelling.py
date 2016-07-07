# coding=utf-8
from selenium import webdriver

driver = webdriver.Firefox()
driver.get("https://www.health2sync.com/fortunetelling/")
elem = driver.find_element_by_class_name("qian").click()
scheight = .1
while scheight < 9.9:
    driver.execute_script("window.scrollTo(0, document.body.scrollHeight/%s);" % scheight)
    scheight += .01
