
const I = actor();

module.exports = {

  fields: {
    email: 'email',
    password: 'password',
  },
  signInButton: 'Sign in',
  registerButton: 'Register',
  url: '/auth/login',


  signIn(email, password) {
    I.fillField(this.fields.email, email);
    I.fillField(this.fields.password, password);
    I.click(this.signInButton);
  },

  goToRegisterPage() {
    I.click(this.registerButton);
  },

  validate() {
    I.amOnPage(this.url);
    I.see('Sign in');
    I.seeInCurrentUrl('/login');
  },

  deleteUser(email, password){
    I.amOnPage(`/auth/temp-admin?email=${email}`);
    I.see('OK');
    I.dontSee('POK');

    I.amOnPage(this.url);
    this.signIn(email, password);
    I.click('Accounts');
    I.click({name: `delete-${email}`});
    I.seeInCurrentUrl(this.url);
  }

}