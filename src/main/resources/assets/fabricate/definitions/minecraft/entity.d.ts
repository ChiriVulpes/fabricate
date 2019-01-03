declare interface Entity {
	posX: number;
	posY: number;
	posZ: number;
}

declare interface EntityLiving extends Entity {

}

declare interface Player extends EntityLiving {

}