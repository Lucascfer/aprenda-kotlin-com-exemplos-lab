enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(val nome: String) {
    override fun toString(): String = nome
}

data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val nivel: Nivel = Nivel.BASICO)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>, val nivel: Nivel = Nivel.BASICO) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        if (usuario in inscritos) {
            println("${usuario.nome} já está matriculado.")
            return
        }

        inscritos.add(usuario)
        println("${usuario.nome} matriculado com sucesso na formação $nome!")
    }

    fun listarConteudos() {
        println("\nConteúdos da formação $nome:")
        conteudos.forEachIndexed { index, conteudo ->
            println("${index + 1}. ${conteudo.nome} (${conteudo.duracao} min) - Nível: ${conteudo.nivel}")
        }
    }

    fun listarInscritos() {
        if (inscritos.isEmpty()) {
            println("Nenhum aluno matriculado ainda.")
            return
        }

        println("\nAlunos matriculados na formação $nome:")
        inscritos.forEachIndexed { index, usuario ->
            println("${index + 1}. ${usuario.nome}")
        }

    }

    fun duracaoTotal(): Int = conteudos.sumOf { it.duracao }
}

fun main() {
    // Criando alguns usuários
    val usuario1 = Usuario("João Silva")
    val usuario2 = Usuario("Maria Oliveira")
    val usuario3 = Usuario("Carlos Souza")

    // Criando conteúdos educacionais
    val conteudo1 = ConteudoEducacional("Introdução ao Kotlin", 90, Nivel.BASICO)
    val conteudo2 = ConteudoEducacional("Programação Orientada a Objetos", 120, Nivel.INTERMEDIARIO)
    val conteudo3 = ConteudoEducacional("Desenvolvimento Android Avançado", 180, Nivel.DIFICIL)
    val conteudo4 = ConteudoEducacional("Algoritmos e Estruturas de Dados", 150, Nivel.INTERMEDIARIO)

    // Criando formações
    val formacaoKotlin = Formacao(
        "Desenvolvimento Kotlin",
        listOf(conteudo1, conteudo2, conteudo3),
        Nivel.INTERMEDIARIO
    )

    val formacaoAlgoritmos = Formacao(
        "Algoritmos Fundamentais",
        listOf(conteudo4),
        Nivel.BASICO
    )

    // Realizando matrículas
    formacaoKotlin.matricular(usuario1)
    formacaoKotlin.matricular(usuario2)
    formacaoKotlin.matricular(usuario1) // Tentativa de matrícula duplicada
    formacaoAlgoritmos.matricular(usuario3)
    formacaoAlgoritmos.matricular(usuario2)

    // Listando informações
    formacaoKotlin.listarConteudos()
    formacaoKotlin.listarInscritos()
    println("Duração total: ${formacaoKotlin.duracaoTotal()} minutos")

    formacaoAlgoritmos.listarConteudos()
    formacaoAlgoritmos.listarInscritos()
    println("Duração total: ${formacaoAlgoritmos.duracaoTotal()} minutos")

    // Testando nível da formação
    println("\nNível da formação Kotlin: ${formacaoKotlin.nivel}")
    println("Nível do conteúdo Android: ${conteudo3.nivel}")
}