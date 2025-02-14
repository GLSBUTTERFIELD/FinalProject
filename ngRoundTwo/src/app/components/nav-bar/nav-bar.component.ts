import { AuthService } from './../../services/auth.service';
import { CommonModule } from '@angular/common';
import { Component, AfterViewInit } from '@angular/core';
declare var $: any;
import { FormsModule } from '@angular/forms';
import { User } from '../../models/user';

@Component({
  selector: 'app-nav-bar',
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})

export class NavBarComponent {

  showSignUpForm: boolean = false;

  showLoginForm: boolean = false;

  newUser: User = new User();

  loggingInUser: User = new User();

  constructor(
    private authService: AuthService
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
        // this.router.navigateByUrl('/todo');
        console.log(displayingUser);
      }, error: (err) => {
        console.log(err);
      }

  })
}



toggleSignUp() {
  this.showSignUpForm = !this.showSignUpForm;
  this.showLoginForm = false;
  console.log("Show SignUp Form:", this.showSignUpForm);
}

toggleLogin() {
  this.showLoginForm = !this.showLoginForm;
  this.showSignUpForm = false;
  console.log("Show Login Form:", this.showLoginForm);
}


}
