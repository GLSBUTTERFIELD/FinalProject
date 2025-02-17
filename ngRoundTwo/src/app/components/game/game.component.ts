import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GameService } from '../../services/game.service';
import { Game } from '../../models/game';
import { Category } from '../../models/category';

@Component({
  selector: 'app-game',
  imports: [
    CommonModule,
    FormsModule,
  ],
  templateUrl: './game.component.html',
  styleUrl: './game.component.css'
})
export class GameComponent implements OnInit {
games: Game[] = [];
categories: Category[] = [];

  constructor(
    private gameService: GameService,
  ){}

  ngOnInit(): void {
    this.loadGames();
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

}
