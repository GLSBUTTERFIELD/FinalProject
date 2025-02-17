import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './services/auth.service';
import { NavBarComponent } from "./components/nav-bar/nav-bar.component";
import { UserProfileComponent } from "./components/user-profile/user-profile.component";
import { HomeComponent } from "./components/home/home.component";
import { AboutComponent } from "./components/about/about.component";
import { TradeComponent } from "./components/trade/trade.component";
import { GatheringComponent } from "./components/gathering/gathering.component";
import { GameComponent } from './components/game/game.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavBarComponent, UserProfileComponent, HomeComponent, AboutComponent, TradeComponent, GatheringComponent, GameComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ngRoundTwo';



  constructor(
    private auth: AuthService
  ) {}


}
