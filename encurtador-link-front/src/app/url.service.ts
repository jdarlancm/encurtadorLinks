import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UrlService {
  private baseUrl = 'http://localhost:8080/api/urls';

  constructor(private http: HttpClient) {}

  createShortUrl(originalUrl: string): Observable<string> {
    return this.http.post<string>(this.baseUrl, { originalUrl });
  }
}
