import { AuthService } from './../../services/auth.service';
import { CommonModule } from '@angular/common';
import { Component, AfterViewInit } from '@angular/core';
declare var $: any;
import { FormsModule } from '@angular/forms';
import { User } from '../../models/user';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  imports: [
    CommonModule,
    FormsModule,
    RouterLink,
  ],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})

export class NavBarComponent {

  showSignUpForm: boolean = false;

  showLoginForm: boolean = false;

  newUser: User = new User();

  loggingInUser: User = new User();

  userButtons: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router,
  ){
  }
  register(addingUser: User){
    console.log(addingUser);
    if(addingUser){
      addingUser.enabled = true;
      this.authService.register(addingUser).subscribe({

        next: (registeredUser) => {
          this.authService.login(addingUser.username, addingUser.password).subscribe({
            next: (loggedInUser) => {
              // this.router.navigateByUrl('/todo');
            },
            error: (problem) => {
              console.error('NavBarComponent.register(): Error logging in user:');
              console.error(problem);
            }
          });
        },
        error: (fail) => {
          console.error('NavBarComponent.register(): Error registering account');
          console.error(fail);
        }

      });

    }
  }

  login(loginUser: User){
    console.log(loginUser.username + " "+loginUser.password);
    this.authService.login(loginUser.username, loginUser.password).subscribe({
      next: (displayingUser) => {
        this.router.navigateByUrl('/home');
        this.toggleLogin();
        console.log(displayingUser);
      }, error: (err) => {
        console.log(err);
      }
  })
}

toggleSignUp() {
  this.showSignUpForm = !this.showSignUpForm;
  this.showLoginForm = false;
}

toggleLogin() {
  this.showLoginForm = !this.showLoginForm;
  this.showSignUpForm = false;
}
toggleUserButtonView(): boolean {
  return !this.authService.checkLogin();
}

logout() {
  this.authService.logout();
  this.router.navigateByUrl("");
  console.log("Logout button pressed")
}


}
