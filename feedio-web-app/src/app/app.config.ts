import { HttpClient, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideRouter } from '@angular/router';
import { MetaReducer, provideState, provideStore } from '@ngrx/store';
import { provideTranslateService, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { routes } from './app.routes';
import { appReducer } from './store/app/app.reducers';
import { persistState } from './store/meta/persist.metareducer';

const httpLoaderFactory: (http: HttpClient) => TranslateHttpLoader =
  (http: HttpClient) => new TranslateHttpLoader(http, './i18n/', '.json');

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({eventCoalescing: true}),
    provideRouter(routes),
    provideHttpClient(withInterceptorsFromDi()),
    provideAnimationsAsync(),
    provideState('feedio-storage', appReducer),
    provideStore(appReducer, {metaReducers: [persistState as MetaReducer]}),
    provideTranslateService({
      defaultLanguage: 'en',
      loader: {provide: TranslateLoader, useFactory: httpLoaderFactory, deps: [HttpClient]}
    })
  ]
};
