import { Routes } from '@angular/router';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { AboutComponent } from './components/about/about.component';
import { HomeComponent } from './components/home/home.component';
import { GatheringComponent } from './components/gathering/gathering.component';
import { TradeComponent } from './components/trade/trade.component';
import { GameComponent } from './components/game/game.component';
import { AdminComponent } from './components/admin/admin.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo:'home'},
  { path: 'home', component: HomeComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'about', component: AboutComponent},
  {path: 'gathering', component: GatheringComponent},
  {path: 'games', component: GameComponent},
  {path: 'gathering/:gatheringId', component: GatheringComponent},
  {path: 'trade', component: TradeComponent},
  {path: 'userprofile/:userId', component: UserProfileComponent},
  {path: 'userprofile', component: UserProfileComponent}
];
