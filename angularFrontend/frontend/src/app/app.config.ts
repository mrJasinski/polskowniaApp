import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptors, withInterceptorsFromDi } from '@angular/common/http';
import { provideNativeDateAdapter } from '@angular/material/core';
import { AuthInterceptorService } from './modules/auth/authInterceptor.service';

export const appConfig: ApplicationConfig = 
{
  providers: 
  [provideZoneChangeDetection({eventCoalescing: true })
    , provideRouter(routes)
    , provideNativeDateAdapter()
    , provideHttpClient(withInterceptorsFromDi())
    , {provide : HTTP_INTERCEPTORS
      , useClass : AuthInterceptorService
      , multi : true}
  ]
};
