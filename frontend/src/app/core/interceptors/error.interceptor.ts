import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ErrorResponse } from '../models/error-response.model';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = 'Une erreur inconnue est survenue';
        let errorCode = 'ER000000';

        // If the error is from our API, it should have our standard format
        if (error.error && error.error.code && error.error.message) {
          const apiError: ErrorResponse = error.error;
          errorMessage = apiError.message;
          errorCode = apiError.code;
        } else if (error.status === 0) {
          // A client-side or network error occurred
          errorMessage = 'Problème de connexion au serveur';
        } else {
          // Backend returned an unsuccessful response code
          errorMessage = `Erreur ${error.status}: ${error.statusText || errorMessage}`;
        }

        console.error('Error occurred:', errorMessage);
        return throwError(() => ({ code: errorCode, message: errorMessage }));
      })
    );
  }
}