# Web-Automation-OrangeHRM-Selenium-TestNG

## Scenerio:
1. Login to orange hrm demo site https://opensource-demo.orangehrmlive.com/

2. Create 2 new employees and save it to a JSON list

3. Now go to PIM dashboard and search by 1st user name. Assert that the user is found.

4. Now click on the user from the search table and update id by random userid

5. Now again search the user by new user id from the PIM dashboard menu and assert that the user is found

6. Now logout from admin and login with the 2nd user from your JSON list

7. Now click on My Info menu

8. Select Gender and Blood Type and save it

9. Click on contact details and input address and email

10. Logout the user

## Technology and Tool Used:
- Selenium Webdriver
- TestNG
- Java
- Gradle
- intellij idea
- Allure

## How to run this project:
- clone this project
- hit the following command into the terminal:
    - gradle clean test
- For generating Report in Allure hit
    - allure generate allure-results --clean -o allure-report
    - allure serve allure-results
    
## Report:
![Screenshot 2023-04-01 204901](https://user-images.githubusercontent.com/71556293/229297221-2d8ff6c5-179c-460f-8bfc-9ca7ad0ed882.png)
![2](https://user-images.githubusercontent.com/71556293/229297236-932cd9c2-54d4-4149-8a0d-53029866e77d.png)
![3](https://user-images.githubusercontent.com/71556293/229297243-aaac5086-a319-40c9-863c-cf8139e19872.png)
![4](https://user-images.githubusercontent.com/71556293/229297251-f629acb2-3c38-466a-9cd4-7cd128b8727d.png)
