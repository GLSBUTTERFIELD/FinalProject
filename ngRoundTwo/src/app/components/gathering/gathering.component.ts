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
  showNewEventForm: boolean = false;
  newGathering: Gathering = new Gathering();
  editGathering: Gathering | null = null;
  gatheringToDisplay: Gathering | null = null;
  showGatheringEditForm: boolean = false;


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

  addGathering(newGathering: Gathering) {
    newGathering.address.id = 1;

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
  setEditGathering() : void {
    this.editGathering = Object.assign({}, this.gatheringToDisplay)
  }

  updateGathering(gathering: Gathering) {
    console.log(gathering)
    this.gatheringService.update(gathering).subscribe({
      next: (event) => {
        this.toggleEditGathering();
      },
      error: (error) => {
        console.log(error);
        console.log("Error updating gathering");
      }
    });
  }

  deleteGathering(gatheringId: number){
    console.log(gatheringId);
    this.gatheringService.destroy(gatheringId).subscribe({
      next: () => {
       this.toggleEditGathering();

      },
      error: (fail) => {
        console.log('gatheringComponent.deleteGathering: failed to delete the Gathering.')
        console.log(fail);
      }
    })
  }

}
