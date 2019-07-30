// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
const hostname = window.location.hostname;

export const environment = {
  production: false,
  baseUrlRestServices: 'http://' + hostname + ':8081/services/rest/',
  qrUrl: 'http://' + hostname + ':4200/my-code',
  streamUrl: 'http://' + hostname + ':8081/stream/subscribe',
  localStorageUuidKey: 'jtquuid'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
