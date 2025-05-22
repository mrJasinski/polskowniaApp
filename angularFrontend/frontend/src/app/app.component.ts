import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { FormsModule } from '@angular/forms';
import { AuthService } from './modules/auth/auth.service';

@Component
({
  selector: 'app-root'
  , templateUrl: './app.component.html'
  , styleUrl: './app.component.css'
  , imports: 
    [
      RouterOutlet
      , HeaderComponent
      , FormsModule
    ]
})
export class AppComponent implements OnInit
{
  title = 'Polskownia';

  constructor(private authService: AuthService)
  {

  }

  ngOnInit() 
  {
    this.authService.autoLogin();
  }
}
