
const I = actor();

module.exports = {

  fields: {
    email: 'email',
    firstName: 'firstName',
    lastName: 'lastName',
    street: 'street',
    npa: 'npa',
    city: 'city',
    password: 'password',
  },
  placeholders: {
    email: 'E',
    firstName: 'First name',
    lastName: 'Last name',
    street: 'Street',
    npa: 'NPA',
    city: 'City',
    password: 'Password',
  },

  url: '/auth/register',
  registerButton: 'Register',

  submitForm(){
    I.click(this.registerButton);
  },

  register(email, firstName, lastName, street, npa, city, password) {
    I.fillField(this.fields.email, email);
    I.fillField(this.fields.firstName, firstName);
    I.fillField(this.fields.lastName, lastName);
    I.fillField(this.fields.street, street);
    I.fillField(this.fields.npa, npa);
    I.fillField(this.fields.city, city);
    I.fillField(this.fields.password, password);
    this.submitForm();
  },

  validateFields(){
    I.amOnPage(this.url);
    locate('input').withAttr({ name: this.fields.email, placeholder: this.placeholders.email});
    locate('input').withAttr({ name: this.fields.firstName, placeholder: this.placeholders.firstName});
    locate('input').withAttr({ name: this.fields.lastName, placeholder: this.placeholders.lastName});
    locate('input').withAttr({ name: this.fields.street, placeholder: this.placeholders.street});
    locate('input').withAttr({ name: this.fields.npa, placeholder: this.placeholders.npa});
    locate('input').withAttr({ name: this.fields.city, placeholder: this.placeholders.city});
    locate('input').withAttr({ name: this.fields.password, placeholder: this.placeholders.password});
  },

  validate() {
    I.amOnPage(this.url);
    I.see('Register');
    I.seeInCurrentUrl('/register');
  },

}
