import { InventoryItem } from './../../models/inventory-item';
import { User } from './../../models/user';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Gathering } from '../../models/gathering';
import { GatheringService } from '../../services/gathering.service';

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
  gatheringsHosted: Gathering[] = [];
  selected: User | null = null;
  gatheringsAttending: Gathering[] = [];
  gatheringsAttended: Gathering[] = [];
  viewInventoryItemEditForm: boolean = false;
  newInventoryItemInfo: InventoryItem = new InventoryItem();

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private gatheringService: GatheringService,
    private router: Router,
    private activatedRoute: ActivatedRoute,

  ){}


  ngOnInit(): void {
    this.loadLoggedInUser();
    this.loadGatheringsHosted();
    this.loadFutureGatherings();
    this.loadPastGatherings();
  }

 reload() {
  this.loadLoggedInUser();
    this.loadGatheringsHosted();
 }

  loadProfileById(userId: number) {
    this.userService.show(userId).subscribe({
      next: (user) => {
      // this.selected = user;
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

  setNewInventoryItemInfo(item: InventoryItem) {
    this.newInventoryItemInfo = { ...item };
    this.viewInventoryItemEditForm = true;
  }

  updateProfile(user: User) {
    console.log(user)
    this.userService.update(user).subscribe({
      next: (User) => {
        this.editProfile = null;
        this.selected = null;
        this.reload();

      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  deleteUser(userId: number){
    console.log(userId);
    this.userService.destroy(userId).subscribe({
      next: () => {
        this.authService.logout();
        this.router.navigateByUrl('/home');
      },
      error: (fail) => {
        console.log('UserProfileComponent.deleteUser: failed to delete the User.')
        console.log(fail);
      }
    })
  }

  loadGatheringsHosted() {
    this.gatheringService.showHostedGatherings().subscribe({
      next:(gatherings)=> {
        this.gatheringsHosted = gatherings;
      },
      error: (err:any)=>{
        console.log('UserProfileComponent.loadGatheringsHosted(): failed to load gatherings hosted.')
        console.log(err);
      }
    })
  }

  loadFutureGatherings() {
    this.gatheringService.loadFutureGatherings().subscribe({
      next:(gatherings)=> {
        this.gatheringsAttending = gatherings;
      },
      error: (err:any)=>{
        console.log('UserProfileComponent.loadFutureGatherings(): failed to load future gatherings.')
        console.log(err);
      }
    })
  }

  loadPastGatherings() {
    this.gatheringService.loadPastGatherings().subscribe({
      next:(gatherings)=> {
        this.gatheringsAttended = gatherings;
      },
      error: (err:any)=>{
        console.log('UserProfileComponent.loadPastGatherings(): failed to load past gatherings.')
        console.log(err);
      }
    })
  }

  closeModal() {
    this.viewInventoryItemEditForm = false;
    this.newInventoryItemInfo = new InventoryItem;
  }

  updateItem(inventoryItemInfo: InventoryItem) {
    console.log('New item Info:', this.newInventoryItemInfo);
    // this.inventoryService.update(this.newInventoryItemInfo).subscribe(...)
    this.closeModal();
  }
}
