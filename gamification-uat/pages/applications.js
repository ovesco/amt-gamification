
const I = actor();

module.exports = {


  fields: {
    email: 'email',
    password: 'password',
  },
  myApplicationsButton: 'Applications',
  newApplicationButton: 'New application',
  myProfileButton: 'My profile',
  logoutButton: 'Logout',
  url: '/developer/applications',

  logout(){
    I.click(this.logoutButton);
  }
}
