import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { environment } from '../../../environments/environment';
import { User } from '../../models/user';
import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-admin',
  imports: [
    CommonModule,
    FormsModule],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit{
  private url = environment.baseUrl + 'api/user';

users: User[] = [];
user: User = new User();
enabled: boolean = false;

constructor(
  private userService: UserService,
  private authService: AuthService,
) {}

ngOnInit(): void {
  this.showAllUsers();
}

reload() {
  this.showAllUsers();
 }

showAllUsers() {
  this.userService.showAll().subscribe({
    next: (users) => {
      this.users = users;
      if (this.users.length > 0) {
        // Assign the first user or whichever user you need
        this.user = { ...this.users[0] }; // Create a shallow copy of the user to prevent reference issues
      }
      // this.reload();
    },
    error: (err) => {
      console.log("failed to load all users");
    }
  })
}

onToggleChange() {
  this.enabled= !this.enabled;
  console.log('Checkbox toggled: enabled?' + this.user.enabled);
  this.userService.update(this.user).subscribe({
    next: (response) => {
      console.log('User updated successfully:', response);
    },
    error: (err) => {
      console.log('Error updating user:', err);
    }
  });
}
}

