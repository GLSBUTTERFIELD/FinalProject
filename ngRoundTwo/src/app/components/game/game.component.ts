import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GameService } from '../../services/game.service';
import { Game } from '../../models/game';
import { Category } from '../../models/category';
import { CategoryService } from '../../services/category.service';
import { User } from '../../models/user';
import { AuthService } from '../../services/auth.service';
import { GameResource } from '../../models/game-resource';
import { GameResourceService } from '../../services/game-resource.service';

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
newGameCategories: Category[] = [];
currentUser: User | null = null;
isLoggedIn: boolean = false;
editGame: Game = new Game();
viewGameEditForm: boolean = false;
resources: GameResource[] = [];
newGameResource: GameResource = new GameResource();
showNewGameResourceForm: boolean = false;


  constructor(
    private gameService: GameService,
    private categoryService: CategoryService,
    private authService: AuthService,
    private gameResourceService: GameResourceService,
  ){}

  ngOnInit(): void {
    this.loadGames();
    // this.loadCategories();
    this.loadCurrentUser();
    this.isLoggedIn = this.authService.checkLogin();
    this.loadGameResources();

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

  loadGameResources() {
    this.gameResourceService.showAll().subscribe({
      next: (resources) => {
      this.resources = resources;
    },
    error: (err) => {
      console.log("GameComponent.loadGameResources: failed to load Game Resource list");
    }
    })
  }

  toggleNewGameForm(){
    this.showNewGameForm = !this.showNewGameForm;
  }

  toggleNewGameResourceForm(){
    this.showNewGameResourceForm = !this.showNewGameResourceForm;
  }

  addGame(newGame: Game) {
      this.gameService.create(newGame).subscribe({
        next: (newGameEntity) => {
          this.newGame = new Game();
          this.loadGames();
          this.toggleNewGameForm();
        },
        error: (err:any) => {
          console.error('Error creating game in Game component' + err);
        }
      });
    }

    setEditGame(gameEdit: Game){
      this.editGame = JSON.parse(JSON.stringify(gameEdit));
    this.viewGameEditForm = true;
    }

    updateGame(game: Game){
      console.log('waaaaahhhhhhh, the button is working!!!');
      this.gameService.update(game).subscribe({
        next: (updatedGame) => {

        },
        error: (error) => {
          console.log(error);
        }
      });
    }

    // loadCategories(){
    //   this.categoryService.listCategories().subscribe({
    //     next: (categories)=>{
    //       this.categories = categories;
    //     },
    //     error: (err:any)=>{
    //       console.error('Error retrieving list of categories in Game Component' + err);
    //     }
    //   })
    // }


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



    closeModal() {
        this.viewGameEditForm = false;
        this.editGame = new Game;
      }

addGameResource(newGameResource: GameResource, gameId: number){
  this.gameResourceService.create(newGameResource, gameId).subscribe({
    next: (newResource) => {
      this.newGameResource = new GameResource;
      this.loadGameResources();
      this.toggleNewGameResourceForm();
      this.loadGames();
    },
    error: (err) => {
      console.log("GameComponent.loadGameResources: failed to load Game Resource list");
    }
    })
  }

}

