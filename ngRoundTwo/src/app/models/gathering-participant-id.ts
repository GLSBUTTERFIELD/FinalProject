export class GatheringParticipantId {
  gatheringId: number;
  userId: number;

  constructor(
    gatheringId: number = 0,
  userId: number = 0,
  ){
    this.gatheringId = gatheringId;
    this.userId = gatheringId;
  }
}
