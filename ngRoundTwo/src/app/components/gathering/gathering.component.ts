import { CommonModule } from '@angular/common';
import { Gathering } from '../../models/gathering';
import { GatheringService } from './../../services/gathering.service';

import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

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

}
