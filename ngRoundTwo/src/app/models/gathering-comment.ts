export class GatheringComment {
  id: number;
  comment: string;
  createDate: string;
  enabled: boolean;

  constructor(
    id: number = 0,
    comment: string = '',
    createDate: string = '',
    enabled: boolean = false,
  ){
    this.id = id;
    this.comment = comment;
    this.createDate = createDate;
    this.enabled = enabled;
  }
}
