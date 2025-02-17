import { UserService } from './../../services/user.service';
import { CommonModule } from '@angular/common';
import { Gathering } from '../../models/gathering';
import { GatheringService } from './../../services/gathering.service';

import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User } from '../../models/user';
import { Observable } from 'rxjs';

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
  selected: Gathering | null = null;


  constructor(
    private gatheringService: GatheringService,
    private userService: UserService,
    // private router: Router,

  ){}

  ngOnInit() {
    this.reload();
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
      guestList.push(participant.participantName);
    });
    return guestList;
  }

  /*
  need to have something like the show gathering with the gathering Id going into the method,
  this will then recive all relavant data as the participants are included in with the gathering
  (according to postman), will need to do a for loop to parse the user id per participant, then
  with each Id (another for loop) run that through a get User by Id, then parse the users name
  in order to print out each of the users names for whom is RSVPing.
  -- how do we add another RSVPing user?
  -- reverse that logic to unRSVP to the event

  addendum: may need a participantsId model and add that into the gathering participants
  in order to parse each userId
  */
}
