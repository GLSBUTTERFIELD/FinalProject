import { Routes } from '@angular/router';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { AboutComponent } from './components/about/about.component';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo:'home'},
  { path: 'home', component: HomeComponent},
  { path: 'about', component: AboutComponent},
  {path: 'userprofile/:userId', component: UserProfileComponent},
  {path: 'userprofile', component: UserProfileComponent}
];
