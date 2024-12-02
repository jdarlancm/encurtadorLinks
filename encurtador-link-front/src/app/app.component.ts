import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { UrlService } from './url.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FormsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'encurtador-link-front';

  originalUrl = '';
  shortUrl: string | null = null;

  constructor(private urlService: UrlService) {}

  shortenUrl() {
    this.urlService.createShortUrl(this.originalUrl).subscribe(url => {
      this.shortUrl = `http://localhost:8080/${url}`;
    });
  }
}
