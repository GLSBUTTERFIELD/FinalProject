import { InventoryItem } from './../../models/inventory-item';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { TradeService } from '../../services/trade.service';
import { InventoryItemComment } from '../../models/inventory-item-comment';
// import { InventoryItemCommentService } from '../../services/inventory-item-comment.service';

@Component({
  selector: 'app-trade',
  imports: [
    CommonModule
  ],
  templateUrl: './trade.component.html',
  styleUrl: './trade.component.css'
})
export class TradeComponent implements OnInit {

  availableItems: InventoryItem[] = [];
  selected: InventoryItem | null = null;
  itemComments: InventoryItemComment[] = [];
  newComment: InventoryItemComment = new InventoryItemComment();
  showNewCommentForm: boolean = false;

  constructor(
    private tradeService: TradeService,
    // private commentService: InventoryItemCommentService,
  ) {}

  ngOnInit() {
    this.reload();
    // this.loadInventoryItemComments();
  }

  reload() {
    this.tradeService.index().subscribe({
      next: (items) => {
        console.log('Trades loaded:', items);
        this.availableItems = items;
      },
      error: (fail) => {
        console.log('TradeComponent.reload: failed to load trade list.', fail);
      }
    });
  }

  showItem(inventoryItemId: number){
    this.tradeService.getInventoryItemById(inventoryItemId).subscribe({
      next: (inventoryItem) =>{
        this.selected = inventoryItem;
      },
      error: (fail) => {
        console.log('InventoryItemComponent.showInventoryItem: failed to load inventoryItem.', fail);
      },
    })
  }

  // loadInventoryItemComments(){
  //   this.commentService.showAll().subscribe({
  //     next:(comments) =>{
  //       this.itemComments = comments;
  //     },
  //     error: (fail) =>{
  //       console.log('InventoryItemComponent.loadInventoryItemComments: failed to load inventoryItemComments.', fail);
  //     },
  //   })
  // }

  // addComment(newComment: InventoryItemComment){
  //   this.commentService.create(newComment).subscribe({
  //     next: (addedComment) => {
  //       this.newComment = new InventoryItemComment();
  //       this.loadInventoryItemComments();
  //       this.toggleNewCommentForm();
  //     },
  //     error: (err) => {
  //       console.log("TradeComponent.addComment: failed to add inventory item comment");
  //     }
  //     })
  //   }

  //   toggleNewCommentForm(){
  //     this.showNewCommentForm = !this.showNewCommentForm;
  //   }

}
