
import { UserService } from './../../services/user.service';
import { CommonModule } from '@angular/common';
import { Gathering } from '../../models/gathering';
import { GatheringService } from './../../services/gathering.service';

import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User } from '../../models/user';
import { catchError, Observable } from 'rxjs';
import { AddressService } from '../../services/address.service';
import { Address } from '../../models/address';
import { Game } from '../../models/game';
import { HttpClient } from '@angular/common/http';
import { GameService } from '../../services/game.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-gathering',
  imports: [
    CommonModule,
    FormsModule,
  ],
  templateUrl: './gathering.component.html',
  styleUrl: './gathering.component.css'
})
export class GatheringComponent implements OnInit{

  gatherings: Gathering[] = [];
  addresses: Address[] = [];
  selected: Gathering | null = null;
  showNewEventForm: boolean = false;
  showNewAddressForm: boolean = false;
  newGathering: Gathering = new Gathering();
  newAddress: Address = new Address();
  editGathering: Gathering | null = null;
  gatheringToDisplay: Gathering | null = null;
  showGatheringEditForm: boolean = false;
  currentUser: User | null = null;
  games: Game[] = [];


  constructor(
    private gatheringService: GatheringService,
    private userService: UserService,
    private addressService: AddressService,
    private http: HttpClient,
    private gameService: GameService,
    private authService: AuthService,
    // private router: Router,
  ){}

  ngOnInit() {
    this.reload();
    this.reloadAddreses();
    this.loadCurrentUser();
    this.reloadGames();
  }

  reload() {
    this.gatheringService.index().subscribe({
      next: (gatheringList) => {
        console.log('Gatherings loaded:', gatheringList);
        this.gatherings = gatheringList;
      },
      error: (fail) => {
        console.log('PostComponent.reload: failed to load gathering list.', fail);
      }
    });
  }

  reloadAddreses() {
    this.addressService.showAllAddresses().subscribe({
      next: (addressList) => {
        console.log('Address loaded:', addressList);
        this.addresses = addressList;
      },
      error: (fail) => {
        console.log('GatheringComponent.reloadAddresses(): failed to load address list.', fail);
      }
    });
  }
  loadCurrentUser() {
    this.authService.getLoggedInUser().subscribe({
      next:(loggedInUser) =>{
        this.currentUser = loggedInUser;
      },
      error: (err) =>{
        console.log('GatheringComponent.loadCurrentUser(): failed to get current user.', err);

      }
    })
  }

  reloadGames() {
    this.gameService.showAll().subscribe({
      next: (gamesList) => {
        console.log('Games loaded:', gamesList);
        this.games = gamesList;
      },
      error: (fail) => {
        console.log('GameComponent.reload: failed to load games list.', fail);
      }
    });
  }

  showGathering(gatheringId: number){
    this.gatheringService.getGatheringById(gatheringId).subscribe({
      next: (gathering) =>{
        console.log(gathering.host);
        this.selected = gathering;
      },
      error: (fail) => {
        console.log('GatheringComponent.showGathering: failed to load gathering.', fail);
      },
    })
  }

  generateGuestList():string[]{
    let guestList:string[] = [];
    console.log(this.selected);
    this.selected?.participants.forEach(function(participant){
      guestList.push(participant.participantUsername);
    });
    return guestList;
  }

  toggleNewEventForm() {
    this.showNewEventForm = !this.showNewEventForm;
  }

  toggleEditGathering() {
    this.showGatheringEditForm = !this.showGatheringEditForm;
  }

  setEditGathering(){
    if (this.selected) {
      this.editGathering = { ...this.selected };
       //i didnt understand the previous logic for this, used some from my bluepix project
      this.showGatheringEditForm = true;
    }
  }

  addGathering(newGathering: Gathering) {
    if (!newGathering.address) {
      alert('Please select an address.');
      return;
    }

    this.gatheringService.create(newGathering).subscribe({
      next: (gathering) => {
        this.newGathering = new Gathering();
        this.reload();
        this.toggleNewEventForm();
      },
      error: (err) => {
        console.error('Error creating gathering in gathering component');
      }
    });
  }

  addNewAddress(newAddress: Address) {
    if (!newAddress.stateAbbr || !newAddress.zip) {
      alert('Please enter valid state abbreviation and zip code.');
      return;
    }
    this.addressService.create(newAddress).subscribe({
      next: (gathering) => {
        this.newAddress = new Address();
        this.reloadAddreses();
        this.showNewAddressForm = false;
        alert('Address created successfully!');
      },
      error: (err) => {
        console.error('Error creating address in address component');
      }
    });
  }

  updateGathering(gathering: Gathering) {
    console.log(gathering)
    this.gatheringService.update(gathering).subscribe({
      next: (event) => {
        this.editGathering = null;
        this.selected = gathering;
      },
      error: (error) => {
        console.log(error);
        console.log("Error updating gathering");
      }
    });
  }

  deleteGathering(gatheringId: number){
    console.log(gatheringId);
    if (confirm("Are you sure you want to delete this gathering?")) {
      // added allert to make sure someone didnt phat finger the delete button
    this.gatheringService.destroy(gatheringId).subscribe({
      next: () => {
       this.toggleEditGathering();
       this.editGathering = null;
       this.selected = null;
       this.reload();

      },
      error: (fail) => {
        console.log('gatheringComponent.deleteGathering: failed to delete the Gathering.')
        console.log(fail);
      }
    });
  }
  }

addGatheringParticipant(gatheringId: number){
  this.gatheringService.addAttendee(gatheringId).subscribe({
    next:()=>{
      this.selected = null;
      this.reload();
    },
    error:(fail)=> {
      console.log('gatheringComponent.addGatheringParticipant(): failed to add GatheringParticipant');
      console.log(fail);
    }
  });
}


}
