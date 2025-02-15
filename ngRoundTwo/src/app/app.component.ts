import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './services/auth.service';
import { NavBarComponent } from "./components/nav-bar/nav-bar.component";
import { UserProfileComponent } from "./components/user-profile/user-profile.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavBarComponent, UserProfileComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ngRoundTwo';



  constructor(
    private auth: AuthService
  ) {}


}
