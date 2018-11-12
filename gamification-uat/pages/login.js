
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

  register() {
    I.click(this.registerButton);
  },

  validate() {
    I.amOnPage(this.url);
    I.see('Sign in');
    I.seeInCurrentUrl('/login');
  }
}