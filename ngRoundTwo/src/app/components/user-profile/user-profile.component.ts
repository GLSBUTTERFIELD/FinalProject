import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../models/user';

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

  selected: User | null = null;

  constructor(
    private userService: UserService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ){}

  loadProfileById(userId: number) {
    this.userService.show(userId).subscribe({
      next: (user) => {
      this.selected = user;
    },
      error: (err) => {
        this.router.navigateByUrl('User ' + userId + ' not found');
        console.error(err);
        console.error("Error loading User in userProfile component");
      }
    });
  }

}
