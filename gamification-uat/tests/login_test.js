Feature('Testing if login page is working');

Scenario('Login page is available', (I, loginPage) => {
    loginPage.validate();
});

Scenario('Login page can redirect to register', (I, loginPage) => {
    I.amOnPage(loginPage.url);
    loginPage.register();
    I.see('Register');
    I.seeInCurrentUrl('/register');
});

Scenario('Login fails with wrong credentials', (I, loginPage) => {
    I.amOnPage(loginPage.url)

    loginPage.signIn('fake@email.com', 'wrong password');

    I.seeInCurrentUrl(loginPage.url);
});
/*
Scenario('Login succeeds with correct credentials', (I, loginPage, registerPage) => {
    I.amOnPage(registerPage.url);
    let user = JSON.parse(jsonUsers)[0];
    registerPage.register(user.email, user.firstName, user.lastName, user.street,user.npa, user.city, "1234567");
})
*/