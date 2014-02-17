package eu.solidcraft.defhorror

class ChujowyService {
    static def triangulate() {
        mamWyjebane().collect{SramNaKolegow it -> it.name}.first().abra()
    }

    static def mamWyjebane() {
        [new SramNaKolegow()]
    }
}


