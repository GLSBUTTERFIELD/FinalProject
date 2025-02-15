import { Routes } from '@angular/router';
import { UserProfileComponent } from './components/user-profile/user-profile.component';

export const routes: Routes = [
  {path: 'userprofile/:userId', component: UserProfileComponent},
  {path: 'userprofile', component: UserProfileComponent}
];
