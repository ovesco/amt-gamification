
const I = actor();

module.exports = {
  fields: {
    appName: 'name',
    appDescription: 'description',
  },
  createAppButton:'create app',
  url: '/developer/application',

  createNewApp(appName, appDescription){
    I.fillField(this.fields.appName, appName);
    I.fillField(this.fields.appDescription, appDescription);
    I.click(this.createAppButton);
  },
}
