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

  constructor(
    private gatheringService: GatheringService,
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

  
}
