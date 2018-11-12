let usersData = require('../users.js');

Feature('TESTING_FUNCTIONAL');

Scenario('Devlopper creates account', (I, registerPage, loginPage) => {
    let user = usersData.users[0];

    I.amOnPage(registerPage.url);
    registerPage.register(user.email, user.firstName, user.lastName, user.street,user.npa, user.city, user.password);
    I.seeInCurrentUrl(loginPage.url);
});

Scenario('Devlopper logs in', (I, loginPage, applicationsPage) =>{
    let user = usersData.users[0];

    I.amOnPage(loginPage.url);
    loginPage.signIn(user.email, user.password);
    I.seeInCurrentUrl(applicationsPage.url);
    I.see('Applications');
});

Scenario('Devlopper creates 25 apps',(I, loginPage, applicationsPage, applicationPage) =>{
    let user = usersData.users[0];

    I.amOnPage(loginPage.url);
    loginPage.signIn(user.email, user.password);
    I.seeInCurrentUrl(applicationsPage.url);
    I.see('Applications');
    
    for(i = 0; i < 25; i++){

        I.click(applicationsPage.newApplicationButton);
        I.seeInCurrentUrl(applicationPage.url);

        applicationPage.createNewApp(`App ${i+1}`, `App description ${i+1}`);
        I.seeInCurrentUrl(applicationsPage.url);

        if(i > 4){
            I.amOnPage(applicationsPage.url + `?page=${Math.floor(i/5)+1}`);
        }
        I.see(`App ${i+1}`);
        I.see(`App description ${i+1}`);
    }

    
});
