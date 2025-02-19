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
newGame: Game = new Game();
showNewGameForm: boolean = false;

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

  toggleNewGameForm(){
    this.showNewGameForm = !this.showNewGameForm;
  }

  addGame(newGame: Game) {
      this.gameService.create(newGame).subscribe({
        next: () => {
          this.newGame = new Game();
          this.loadGames();
          this.toggleNewGameForm();
        },
        error: (err:any) => {
          console.error('Error creating game in Game component' + err);
        }
      });
    }

}
