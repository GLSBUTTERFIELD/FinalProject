import { User } from './../../models/user';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-user-profile',
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent {

  userToDisplay: User | null = null;
  editProfile: User | null = null;

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ){}


  ngOnInit(): void {
    this.loadLoggedInUser();
  }

  loadProfileById(userId: number) {
    this.userService.show(userId).subscribe({
      next: (user) => {
    },
      error: (err) => {
        this.router.navigateByUrl('User ' + userId + ' not found');
        console.error(err);
        console.error("Error loading User in userProfile component");
      }
    });
  }

  loadLoggedInUser(){
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        this.userToDisplay = user;
    },
      error: (err) => {
        console.error(err);
        console.error("Error loading User in userProfile component");
      }
    });
  }

  setEditProfile() : void {
    this.editProfile = Object.assign({}, this.userToDisplay)
  }

  updateProfile(user: User) {

    this.userService.update(user).subscribe({
      next: (updatedUser) => {
        // reloads user and exits the update form
        this.userToDisplay = updatedUser;
      this.editProfile = null;
      },
      error: (error) => {
        console.log(error);
        console.log("Error updating profile in user-profile component");
      }
    });
  }

  deletePost(userId: number){
    console.log(userId);
    this.userService.destroy(userId).subscribe({
      next: () => {
        this.authService.logout();
        this.router.navigateByUrl('/home');
      },
      error: (fail) => {
        console.log('homeComponent.deletePost: failed to delete the Post.')
        console.log(fail);
      }
    })
  }
}
