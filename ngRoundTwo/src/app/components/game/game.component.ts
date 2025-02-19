import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GameService } from '../../services/game.service';
import { Game } from '../../models/game';
import { Category } from '../../models/category';
import { CategoryService } from '../../services/category.service';

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
    private categoryService: CategoryService,
  ){}

  ngOnInit(): void {
    this.loadGames();
    this.loadCategories();
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

    loadCategories(){
      this.categoryService.listCategories().subscribe({
        next: (categories)=>{
          this.categories = categories;
        },
        error: (err:any)=>{
          console.error('Error retrieving list of categories in Game Component' + err);
        }
      })
    }

}
