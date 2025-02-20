import { GameService } from './../../services/game.service';
import { ItemConditionService } from './../../services/item-condition.service';
import { TradeService } from './../../services/trade.service';
import { InventoryItem } from './../../models/inventory-item';
import { User } from './../../models/user';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Gathering } from '../../models/gathering';
import { GatheringService } from '../../services/gathering.service';
import { ItemCondition } from '../../models/item-condition';
import { Game } from '../../models/game';
import { Observable, catchError, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-user-profile',
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent implements OnInit{

  private url = environment.baseUrl + 'api/user';
  userToDisplay: User | null = null;
  editProfile: User | null = null;
  gatheringsHosted: Gathering[] = [];
  selected: User | null = null;
  gatheringsAttending: Gathering[] = [];
  gatheringsAttended: Gathering[] = [];
  conditions: ItemCondition[] = [];
  viewInventoryItemEditForm: boolean = false;
  newInventoryItemInfo: InventoryItem = new InventoryItem();
  newInventoryItem: InventoryItem = new InventoryItem();
  viewNewInventoryItemForm: boolean = false;
  games: Game[] = [];
  users: User[] = [];

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private gatheringService: GatheringService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private tradeService: TradeService,
    private itemConditionService: ItemConditionService,
    private gameService: GameService,
    private http: HttpClient,
  ){}


  ngOnInit(): void {
    this.loadLoggedInUser();
    this.loadGatheringsHosted();
    this.loadFutureGatherings();
    this.loadPastGatherings();
    this.loadConditions();
    this.loadGames();
    this.showAllUsers();
  }

 reload() {
  this.loadLoggedInUser();
    this.loadGatheringsHosted();
 }
 loadConditions() {
  this.itemConditionService.index().subscribe({
    next: (conditionList) => {
      console.log('Conditions loaded:', conditionList);
      this.conditions = conditionList;
    },
    error: (fail) => {
      console.log('UserProfileComponent.loadConditions(): failed to load condition list.', fail);
    }
  });
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
    this.newInventoryItemInfo = JSON.parse(JSON.stringify(item));
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

  showAllUsers() {
      this.userService.showAll().subscribe({
        next: (users) => {
          this.users = users;
          this.reload();
        },
        error: (err) => {
          console.log("failed to load all users");
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
    this.viewNewInventoryItemForm = false;
  }

  updateItem(inventoryItemInfo: InventoryItem) {
    console.log('New item Info:', this.newInventoryItemInfo);
    this.tradeService.update(inventoryItemInfo).subscribe({
      next: (inventoryInfo) => {
        this.reload();
        this.closeModal();
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  addInventoryItem(inventoryItem: InventoryItem){
    console.log('New item Info:', this.newInventoryItemInfo);
    this.tradeService.create(inventoryItem).subscribe({
      next: (addedInventoryItem) => {
        this.newInventoryItem = addedInventoryItem;
        this.reload();
        this.closeModal();
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  loadGames() {
    this.gameService.showAll().subscribe({
      next: (games) => {
      this.games = games;
    },
    error: (err) => {
      console.log("GameComponent.loadGames: failed to load Game list");
    }
    })
  }
// added this method
  viewGathering(gatheringId: number) {
    this.router.navigate(['/gathering', gatheringId]);
    console.log(gatheringId);
  }
// --------------------------
}
