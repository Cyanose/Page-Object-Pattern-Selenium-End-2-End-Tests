# Page Object Pattern Selenium end-2-end tests

The project contains the automated end-2-end test suite for the process control web-app.

##### Setup

The web app is dockerized, in order to set it up you need to invoke `docker-compose up -d` command in the root directory of your project (where the docerfile is). Then you can access the app under `http://localhost:4444/` url in your browser. I'm fully aware that We don't need 2 dockerized databases, I just wanted to try setting up one by myself :) 

**Test Scenerios:**

* 1: Successfull user registration (use uniqe email every time).

* 2: Failed user registration - *password* and *confirm password* fields do not match.

* 3: Failed user registration - password does not meet the requirements.
> a) there is no uppercase letter</br>
> b) there is no digit</br>
> c) there is no special character</br>

* 4: Successfull login test.

* 5: Navigataion through menu bar on the left-site. Check if redirection links works as expected.

* 6: Successfull addition of a new process. Check if the process was added to the *Processes* table.

* 7: Successfull addition of a new process. Check if the newly added process is visible on the Dashboard Page 

* 8: Failed addition of a new process.

* 9: Successfull addition of a characteristic to the existing *'DEMO PROJECT'* process. Check if characteristic was added Successfully to the *Characteristics* table.

* 10: Successfull addition of a characteristic to the existing *'DEMO PROJECT'* process. Check if it's visible on the Dashboard Page.

* 11: Failed addition of a characteristic. Upper specification limit was not filled

* 12: Successfull addition of a characteristic to the existing *'DEMO PROJECT'* process, then Successfull addition of a results to this characteristic. Check if metrics in the raport are correct.

* 13: Successfull login of all the freshly registered users. *(Written in order to train fetching test Data from database)*