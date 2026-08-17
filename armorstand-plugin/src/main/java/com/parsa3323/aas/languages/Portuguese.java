/*
 *
 * Copyright
 * 2026 AdvancedArmorStands, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.parsa3323.aas.languages;

import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.api.language.Language;
import com.parsa3323.aas.api.language.Messages;
import com.parsa3323.aas.utils.TextUtils;
import org.bukkit.configuration.file.YamlConfiguration;

public class Portuguese extends Language {
    /*
    Made with AI
    */
    public Portuguese() {
        super(AdvancedArmorStands.plugin, "pt");
        YamlConfiguration yml = getYml();

        yml.addDefault(Messages.MENU_FORMAT, "&7as &8» &7%section%");
        yml.addDefault(Messages.ACTIONS_MENU_TITLE, "ações");
        yml.addDefault(Messages.ACTIONS_MENU_ITEM_LORE, "&7Isso significa que se\n&7você clicar nesse armor\n&7stand, o comando\n&7será executado: %command%\n\n&6» &eClique para editar\n&6» &eShift + clique para remover");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_NAME, "&eCriar uma ação");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_LORE, "&7Selecione isso para criar\n&7uma ação que executa\n&7comandos ao clicar\n\n&eClique para criar");
        yml.addDefault(Messages.ACTIONS_CREATION_MESSAGE, "&aDigite o comando que você quer definir (sem '/'). Digite 'exit' para cancelar.");
        yml.addDefault(Messages.ACTIONS_SETTINGS_MENU_TITLE, "configurações da ação");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_NAME, "&eTipo de Gatilho");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_LORE, "&7Escolha como o comando\n&7será acionado quando\n&7o jogador interagir com\n&7o ArmorStand.\n\n%trigger_list%\n\n&eClique para mudar");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_NAME, "&eRemetente");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_LORE, "&7Escolha quem vai enviar\n&7o comando. Isso afeta como\n&7as permissões e a execução funcionam.\n\n%sender_list%\n\n&eClique para mudar");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_NAME, "&ePrioridade");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_LORE, "&7Altere a prioridade\n&7deste comando. Quando\n&7a prioridade for maior\n&7que a das outras, ele será\n&7executado primeiro.\n\n&6» &e%priority_number%\n\n&eClique para alterar");
        yml.addDefault(Messages.ACTIONS_SETTING_PRIORITY_ALREADY_TAKEN, "&eDuas ou mais ações têm a mesma prioridade, o que pode fazer com que entrem em conflito.");
        yml.addDefault(Messages.AI_MENU_TITLE, "configurações de ia");
        yml.addDefault(Messages.AI_COMMAND_DESCRIPTION, "Peça ajuda para a IA");
        yml.addDefault(Messages.AI_COMMAND_THINKING, "&7Pensando...");
        yml.addDefault(Messages.AI_OPTION_NAME, "&eOpções de IA");
        yml.addDefault(Messages.AI_OPTION_ENABLED_LORE, "&7Opções relacionadas à\n&7inteligência artificial\n&7deste ArmorStand.\n\n&eClique para abrir");
        yml.addDefault(Messages.AI_OPTION_DISABLED_LORE, "&7Ative a IA para os ArmorStands\n&7colocando sua chave de API\n&7na configuração.\n\n&4✘ &cNão ativada");
        yml.addDefault(Messages.AI_SETTINGS_MEMORY_LORE, "&7Adicione instruções personalizadas\n&7para a IA deste ArmorStand\nseguir ao responder.\n\n%current%\n\n&6» &eShift + clique para redefinir\n&6» &eClique para mudar");
        yml.addDefault(Messages.AI_SETTINGS_TOGGLE_LORE, "&7Ative ou desative a IA\n&7deste ArmorStand\n\n%ai_status%");
        yml.addDefault(Messages.AI_MEMORY_EXIT_SUCCESS, "&aVocê saiu da sessão de memória com sucesso.");
        yml.addDefault(Messages.AI_MEMORY_UPDATE_SUCCESS, "&aInstruções do ArmorStand atualizadas com sucesso");
        yml.addDefault(Messages.AI_PREFIX, "&7[&6»&7] &6");
        yml.addDefault(Messages.AI_RESPONSE_FORMAT, "&7[&6»&7] &6%response%");
        yml.addDefault(Messages.AI_RESPONSE_NOT_FOUND, "Erro de IA: não foi possível encontrar o texto do assistente na resposta");
        yml.addDefault(Messages.AI_ERROR, "&cErro de IA: %error%");
        yml.addDefault(Messages.AI_ERROR_HTTP, "&cErro de IA: HTTP %code%, certifique-se de estar conectado à internet");
        yml.addDefault(Messages.AI_HTTP_ERROR, "Erro de IA: HTTP {code}");
        yml.addDefault(Messages.AI_HTTP_ERROR_WITH_INTERNET, "Erro de IA: HTTP {code}, certifique-se de estar conectado à internet");
        yml.addDefault(Messages.AI_PARSE_ERROR, "Erro ao processar a resposta da IA: {error}");
        yml.addDefault(Messages.ANIMATION_COMMAND_DESCRIPTION, "Mostra os comandos de animação");
        yml.addDefault(Messages.ANIMATION_COMMAND_HEADER, "&6&lAdvanced &e&lArmorStands &7&lcomandos de animação");
        yml.addDefault(Messages.ANIMATION_ADD_SUCCESS, "&aAnimação %animation% atribuída ao ArmorStand %armorstand% com sucesso");
        yml.addDefault(Messages.ANIMATION_CLEAR_DESCRIPTION, "Remove as &7animações de um as");
        yml.addDefault(Messages.ANIMATION_CLEAR_SUCCESS, "&aAnimações de %armorstand% removidas com sucesso");
        yml.addDefault(Messages.ANIMATION_CREATE_DESCRIPTION, "&7Crie ou edite uma &7animação com um as");
        yml.addDefault(Messages.ANIMATION_REMOVE_DESCRIPTION, "&7Remove uma animação por completo");
        yml.addDefault(Messages.ANIMATION_REMOVE_SUCCESS, "&aAnimação '%animation%' removida com sucesso");
        yml.addDefault(Messages.ANIMATION_INVALID, "&cAnimação inválida");
        yml.addDefault(Messages.ANIMATION_INVALID_WITH_SUGGESTION, "&cAnimação '%animation%' inválida. Você quis dizer '%suggestion%'?");
        yml.addDefault(Messages.ANIMATION_INVALID_NAME, "&cNome de animação inválido");
        yml.addDefault(Messages.ANIMATION_EDITOR_ALREADY_HAS_ANIMATION, "&cNão é possível abrir o editor em um ArmorStand que já tem animação");
        yml.addDefault(Messages.ANIMATION_EDITOR_GAMEMODE_CHANGED, "&aSeu modo de jogo foi temporariamente alterado para CRIATIVO, pois sessões de edição não funcionam no modo AVENTURA. Ele será restaurado automaticamente quando você sair.");
        yml.addDefault(Messages.ANIMATION_EDITOR_ENTERED, "&aVocê entrou na sessão de criação/edição de animação com sucesso");
        yml.addDefault(Messages.ANIMATIONS_MENU_SUCCESSFUL_CREATION, "&aAnimação salva com sucesso, clique para adicioná-la a este ArmorStand");
        yml.addDefault(Messages.KEYFRAME_MENU_TITLE, "editor de keyframes");
        yml.addDefault(Messages.KEYFRAME_OPTION_LORE, "&eCLIQUE DIREITO &7Adiciona um keyframe\n&eSHIFT + CLIQUE DIREITO &7Abre o menu de keyframes\n\n&8&lAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.KEYFRAME_CREATED, "&aKeyframe adicionado!");
        yml.addDefault(Messages.KEYFRAME_STEP_LORE, "&7Este é o %step%%suffix% passo\n&7da animação %animation%, que\n&7vai colocar o ArmorStand nessa\n&7posição na sua vez\n\n&eClique para remover");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_NAME, "&eIntervalo: %interval%");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_LORE, "&7Define o atraso\n&7entre os quadros da animação.\n&7Quanto menor o valor,\n&7mais rápida a animação.\n\n&6»&e Clique esquerdo para aumentar\n&6»&e Clique direito para diminuir.\n&6»&e Segure Shift para mudar\n&6»&e de 10 em 10.\n\n&eClique para mudar");
        yml.addDefault(Messages.KEYFRAME_LOOP_NAME, "&eRepetir: %loop%");
        yml.addDefault(Messages.KEYFRAME_LOOP_LORE, "&7Define se a animação\n&7deve se repetir em loop\n&7infinitamente desde o início\n\n&eClique para mudar");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_DISPLAY, "&eRealista: %value%");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_LORE, "&7As animações realistas interpolam suavemente\n&7entre as etapas da animação, em vez de\n&7pular instantaneamente de uma pose para outra.\n\n&7Aviso: pode causar lag\n\n&eClique para alterar");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_DISPLAY, "&eFrames realistas: %value%");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_LORE, "&7O número de frames gerados\n&7durante a animação realista\n\n&6»&e Clique esquerdo para aumentar\n&6»&e Clique direito para diminuir.\n&6»&e Segure Shift para alterar\n&6»&e em incrementos de 10.\n\n&eClique para alterar");
        yml.addDefault(Messages.SAVE_MENU_TITLE, "menu de salvamento");
        yml.addDefault(Messages.SAVE_MENU_CREATE_MESSAGE, "&aDigite o nome do tipo que você quer criar (as propriedades deste ArmorStand serão copiadas). Digite 'exit' para sair");
        yml.addDefault(Messages.SAVE_MENU_TYPE_LORE, "&7Selecionar isso vai sobrescrever\n&7a configuração antiga de %type% com\n&7a configuração atual que você fez\n\n&eClique para salvar");
        yml.addDefault(Messages.SAVE_MENU_TYPE_SAVED, "&aPropriedades do ArmorStand salvas em '%type%'");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_NAME, "&eCriar um tipo");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_LORE, "&7Selecione isso para criar\n&7um tipo com as propriedades\n&7deste armor stand\n\n&eClique para criar");
        yml.addDefault(Messages.ARMOR_STAND_MENU_TITLE, "configurações");
        yml.addDefault(Messages.ARMOR_STAND_NOT_LOADED, "&cEste ArmorStand não está carregado");
        yml.addDefault(Messages.ARMOR_STAND_LOAD_SUCCESS, "&aSuportul de armură '%name%' a fost încărcat cu succes");
        yml.addDefault(Messages.ARMOR_STAND_LOAD_FAILED, "&cNu s-a putut încărca suportul de armură. Verifică consola pentru mai multe detalii!");
        yml.addDefault(Messages.ARMOR_STAND_LOAD_LOADED, "&cAcest suport de armură este deja încărcat");
        yml.addDefault(Messages.ARMOR_STANDS_LOAD_SUCCESS, "&aAu fost încărcate cu succes %loaded% suporturi de armură.");
        yml.addDefault(Messages.ARMOR_STANDS_LOAD_FAILED, "&cNu s-au putut încărca %failed% suporturi de armură.");
        yml.addDefault(Messages.ARMOR_STAND_INVALID, "&cArmorStand inválido");
        yml.addDefault(Messages.ARMOR_STAND_INVALID_WITH_SUGGESTION, "&cArmorStand '%armorstand%' inválido. Você quis dizer '%suggestion%'?");
        yml.addDefault(Messages.ARMOR_STAND_NOT_FOUND, "&cArmorStand não encontrado!");
        yml.addDefault(Messages.WORLD_NOT_FOUND, "&cMundo não encontrado!");
        yml.addDefault(Messages.TELEPORTED_TO_ARMOR_STAND, "&aTeleportado para %name%");
        yml.addDefault(Messages.ARMOR_STAND_UPDATED_SUCCESS, "&aArmorStand atualizado com sucesso!");
        yml.addDefault(Messages.ARMOR_STAND_UPDATED_FAILED, "&cFalha ao atualizar o ArmorStand!");
        yml.addDefault(Messages.ARMOR_STAND_UNAVAILABLE, "&cO ArmorStand não está mais disponível!");
        yml.addDefault(Messages.ARMOR_STAND_UNAVAILABLE_TITLE, "&cArmorStand não está disponível");
        yml.addDefault(Messages.ARMOR_STAND_MOVE_SUCCESS, "&aArmorStand movido com sucesso.");
        yml.addDefault(Messages.MESSAGES_NOT_LOADED, "&cParece que %amount% ArmorStands não foram carregados pelo gerador de mundo. Para corrigir isso, ative 'auto-load-armor-stands' na configuração para carregar todos automaticamente.");
        yml.addDefault(Messages.ISSUES_FOUND_ON_JOIN, "&eAdvancedArmorStands encontrou %amount% erro(s) e aviso(s) nesta sessão. Verifique o arquivo de log para mais detalhes.");
        yml.addDefault(Messages.ARMOR_STAND_SAVE_CONFIRM, "&aFaça isso mais %amount% vez%plural% para salvar este ArmorStand.");
        yml.addDefault(Messages.ARMOR_STAND_SAVED_SUCCESS, "&eArmorStand salvo como %name%!");
        yml.addDefault(Messages.ARMOR_STAND_DELETE_CONFIRM, "&cFaça isso mais %amount% vez%plural% para excluir este ArmorStand.");
        yml.addDefault(Messages.ARMOR_STAND_DELETED_SUCCESS, "&aO ArmorStand foi excluído, mas pode ser restaurado usando o comando restore. Ele será excluído completamente quando o servidor reiniciar");
        yml.addDefault(Messages.COMMAND_NO_PERMISSION, "&cVocê não tem permissão para usar este comando!");
        yml.addDefault(Messages.COMMAND_UNKNOWN, "&cO comando '%command%' não é um subcomando válido.");
        yml.addDefault(Messages.COMMAND_UNKNOWN_WITH_SUGGESTION, "&cO comando '%command%' não é um subcomando válido. Você quis dizer '%suggestion%'?");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE_NUMBER, "&cPor favor, digite um número de página válido.");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE, "&cPágina inválida. Escolha uma página entre 1 e %pages%.");
        yml.addDefault(Messages.COMMAND_USAGE_HOVER, "&eClique para usar a sugestão");
        yml.addDefault(Messages.COMMAND_EXAMPLES, "&7Exemplos:");
        yml.addDefault(Messages.COMMAND_CLICK_TO_USE, "&e&lClique para usar este comando");
        yml.addDefault(Messages.COMMAND_LABEL, "&7Comando: &e%command%");
        yml.addDefault(Messages.COMMAND_DESCRIPTION_LABEL, "&7Descrição: &f%description%");
        yml.addDefault(Messages.CREATE_DESCRIPTION, "Cria um &7ArmorStand");
        yml.addDefault(Messages.CREATE_CUSTOM_USAGE, "&cUso: /as create custom <name> <part> <x> <y> <z> [<part> <x> <y> <z> ...]");
        yml.addDefault(Messages.CREATE_INVALID_PART_NUMBER, "&cNúmero inválido para a parte %part%. Uso: <part> <x> <y> <z>");
        yml.addDefault(Messages.CREATE_UNKNOWN_PART, "&cParte desconhecida: %part%");
        yml.addDefault(Messages.DELETE_DESCRIPTION, "Exclui um &7ArmorStand");
        yml.addDefault(Messages.DELETE_ALL_SUCCESS, "&aTodos os ArmorStands excluídos com sucesso");
        yml.addDefault(Messages.DELETE_SUCCESS, "&aArmorStand excluído por completo");
        yml.addDefault(Messages.UNLINK_SUCCESS, "&aSuporte de armadura foi desvinculado com sucesso do AdvancedArmorStands");
        yml.addDefault(Messages.UNLINK_ALL_SUCCESS, "&aTodos os suportes de armadura foram desvinculados com sucesso do AdvancedArmorStands");
        yml.addDefault(Messages.DEBUG_DESCRIPTION, "Mostra informações de depuração");
        yml.addDefault(Messages.HEAD_DESCRIPTION, "Pega a cabeça de um jogador");
        yml.addDefault(Messages.HEAD_SUCCESS, "&aVocê recebeu a cabeça de %player%");
        yml.addDefault(Messages.LIST_DESCRIPTION, "Mostra uma lista de ArmorStands");
        yml.addDefault(Messages.LIST_EMPTY_TITLE, "&c&l     Nenhum ArmorStand Salvo Encontrado ");
        yml.addDefault(Messages.LIST_EMPTY_HINT, "&7Use &e/as create <type> <name>&7 para criar seu primeiro ArmorStand!");
        yml.addDefault(Messages.LOAD_DESCRIPTION, "Carrega um ArmorStand");
        yml.addDefault(Messages.LOAD_NO_UNLOADED, "&eNenhum ArmorStand descarregado encontrado.");
        yml.addDefault(Messages.LOAD_FAILED, "&cFalhou: %failed%");
        yml.addDefault(Messages.MOVE_DESCRIPTION, "Move um ArmorStand para a sua &7localização");
        yml.addDefault(Messages.OPTIONS_DESCRIPTION, "Abre o menu de opções");
        yml.addDefault(Messages.RELOAD_DESCRIPTION, "Recarrega as configurações do plugin");
        yml.addDefault(Messages.RELOAD_SUCCESS, "&aConfiguração do AdvancedArmorStand recarregada com sucesso");
        yml.addDefault(Messages.RELOAD_ERROR, "&cErro ao recarregar o plugin, verifique o console para mais detalhes");
        yml.addDefault(Messages.RENAME_DESCRIPTION, "Renomeia um &7as");
        yml.addDefault(Messages.RENAME_SUCCESS, "&aArmorStand renomeado de '%old_name%' para '%new_name%'");
        yml.addDefault(Messages.RESTORE_DESCRIPTION, "Restaura um ArmorStand excluído");
        yml.addDefault(Messages.RESTORE_NOT_DELETED, "&cEste ArmorStand não está excluído ou já é tarde demais");
        yml.addDefault(Messages.RESTORE_UNKNOWN_ERROR, "&cErro desconhecido, verifique o console para mais informações");
        yml.addDefault(Messages.RESTORE_SUCCESS, "&aArmorStand restaurado com sucesso");
        yml.addDefault(Messages.TELEPORT_NOT_ON_GROUND, "&cEste ArmorStand não está no chão. Tem certeza de que quer se teleportar até ele?");
        yml.addDefault(Messages.TELEPORT_FORCE_HINT, "&cUse '/as teleport %armorstand% --force' para forçar o teleporte");
        yml.addDefault(Messages.TELL_DESCRIPTION, "Faça uma pergunta para a IA de um ArmorStand");
        yml.addDefault(Messages.TELL_AI_DISABLED, "&cEste ArmorStand não tem a IA ativada");
        yml.addDefault(Messages.INVENTORY_EXIT_NAME, "&7» &cSAIR&7 (Clique Direito)");
        yml.addDefault(Messages.INVENTORY_EXIT_LORE, "\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.INVENTORY_ENTER_SESSION, "&aVocê entrou na sessão de edição, clique no item SAIR para voltar e recuperar seus itens");
        yml.addDefault(Messages.EDITOR_EXIT_NAME, "&7» &cSAIR &7(Clique Direito)");
        yml.addDefault(Messages.EDITOR_EXIT_LORE, "\n&8AdvancedArmorStands Editor Item");
        yml.addDefault(Messages.HEAD_ITEM_LORE, "&eCLIQUE DIREITO &8» &7Gira a cabeça para a direita\n&eCLIQUE ESQUERDO &8» &7Gira a cabeça para a esquerda\n&eSHIFT + CLIQUE DIREITO &8» &7Levanta a cabeça\n&eSHIFT + CLIQUE ESQUERDO &8» &7Abaixa a cabeça\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_HAND_ITEM_LORE, "&eCLIQUE DIREITO &8» &7Gira a mão esquerda para a direita\n&eCLIQUE ESQUERDO &8» &7Gira a mão esquerda para a esquerda\n&eSHIFT + CLIQUE DIREITO &8» &7Levanta o braço esquerdo\n&eSHIFT + CLIQUE ESQUERDO &8» &7Abaixa o braço esquerdo\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_HAND_ITEM_LORE, "&eCLIQUE DIREITO &8» &7Gira a mão direita para a direita\n&eCLIQUE ESQUERDO &8» &7Gira a mão direita para a esquerda\n&eSHIFT + CLIQUE DIREITO &8» &7Levanta o braço direito\n&eSHIFT + CLIQUE ESQUERDO &8» &7Abaixa o braço esquerdo\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_LEG_ITEM_LORE, "&eCLIQUE DIREITO &8» &7Gira a perna esquerda para a direita\n&eCLIQUE ESQUERDO &8» &7Gira a perna esquerda para a esquerda\n&eSHIFT + CLIQUE DIREITO &8» &7Levanta a perna esquerda\n&eSHIFT + CLIQUE ESQUERDO &8» &7Abaixa a perna esquerda\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_LEG_ITEM_LORE, "&eCLIQUE DIREITO &8» &7Gira a perna direita para a direita\n&eCLIQUE ESQUERDO &8» &7Gira a perna direita para a esquerda\n&eSHIFT + CLIQUE DIREITO &8» &7Levanta a perna direita\n&eSHIFT + CLIQUE ESQUERDO &8» &7Abaixa a perna direita\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.ROTATE_ITEM_LORE, "&eCLIQUE DIREITO &8» &7Gira para a direita\n&eCLIQUE ESQUERDO &8» &7Gira para a esquerda\n&eSHIFT + CLIQUE DIREITO (Bloco) &8» &7Levanta o corpo\n&eSHIFT + CLIQUE ESQUERDO (Bloco) &8» &7Abaixa o corpo\n&eSHIFT + CLIQUE DIREITO (Ar) &8» &7Move o corpo para a direita\n&eSHIFT + CLIQUE ESQUERDO (Ar) &8» &7Move o corpo para a esquerda\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.SAVE_ITEM_LORE, "&7Clique para salvar a configuração\n&7do ArmorStand como um tipo, para usar depois\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.EDIT_SESSION_BLOCK_BREAK, "&cVocê não pode quebrar blocos na sessão de edição.");
        yml.addDefault(Messages.MEMORY_SESSION_BLOCK_BREAK, "&cVocê não pode quebrar blocos aqui.");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_START, "&aDigite no chat o nome que você quer definir. Para sair digite 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_ALREADY, "&cVocê já está em uma sessão de definição de nome");
        yml.addDefault(Messages.CUSTOM_NAME_EXIT_SUCCESS, "&aVocê saiu da sessão de definição de nome com sucesso");
        yml.addDefault(Messages.CUSTOM_NAME_SET_SUCCESS, "&aNome personalizado do ArmorStand definido como '%name%' com sucesso");
        yml.addDefault(Messages.TYPE_CREATE_EXIT_SUCCESS, "&aVocê saiu da sessão de criação de tipo com sucesso");
        yml.addDefault(Messages.TYPE_ALREADY_EXISTS, "&cEsse tipo já existe. Escolha outro nome ou selecione o tipo no menu de salvamento");
        yml.addDefault(Messages.TYPE_CREATED_SUCCESS, "&aTipo '%type%' criado com as propriedades deste ArmorStand");
        yml.addDefault(Messages.CREATION_CANCELLED, "&cCriação cancelada por inatividade");
        yml.addDefault(Messages.DELETION_CANCELLED, "&cExclusão cancelada por inatividade.");
        yml.addDefault(Messages.ARMS_OPTION_NAME, "&eBraços");
        yml.addDefault(Messages.ARMS_OPTION_LORE, "&7Ative ou desative\n&7os braços deste ArmorStand \n\n%status%");
        yml.addDefault(Messages.BASE_PLATE_OPTION_NAME, "&eBase");
        yml.addDefault(Messages.BASE_PLATE_OPTION_LORE, "&7Ative ou desative\n&7a base deste ArmorStand \n\n%status%");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NAME, "&eNome personalizado");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_LORE, "&7Defina um nome personalizado\n&7para este ArmorStand");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_CURRENT_NAME, "&eAtual&6 » &e");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NO_NAME, "&eNome atual&6 » &eNenhum");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ENTER_NAME, "&aDigite no chat o nome que você quer definir. Para sair digite 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ALREADY_EDITING, "&cVocê já está em uma sessão de definição de nome");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_NAME, "&eNome personalizado visível");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_LORE, "&7Ative ou desative\n&7o nome personalizado deste ArmorStand\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_NAME, "&eBrilho");
        yml.addDefault(Messages.GLOWING_OPTION_LORE, "&7Ative ou desative\n&7o brilho deste ArmorStand\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Está brilhando");
        yml.addDefault(Messages.GLOWING_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Não está brilhando");
        yml.addDefault(Messages.SMALL_OPTION_NAME, "&ePequeno");
        yml.addDefault(Messages.SMALL_OPTION_LORE, "&7Ative ou desative\n&7o tamanho pequeno deste ArmorStand\n\n%status%");
        yml.addDefault(Messages.SMALL_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e É pequeno");
        yml.addDefault(Messages.SMALL_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Não é pequeno");
        yml.addDefault(Messages.VISIBLE_OPTION_NAME, "&eVisibilidade");
        yml.addDefault(Messages.VISIBLE_OPTION_LORE, "&7Ative ou desative\n&7a visibilidade deste ArmorStand\n\n%status%");
        yml.addDefault(Messages.VISIBLE_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Está visível");
        yml.addDefault(Messages.VISIBLE_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Não está visível");
        yml.addDefault(Messages.DELETE_TOOL_NAME, "&eExcluir");
        yml.addDefault(Messages.DELETE_TOOL_LORE, "&7Exclui este ArmorStand\n&7essa ação não pode ser desfeita\n\n&eClique para excluir");
        yml.addDefault(Messages.DELETE_TOOL_MESSAGE, "&aO ArmorStand foi excluído, mas pode ser restaurado usando o comando restore. Ele será excluído completamente quando o servidor reiniciar");
        yml.addDefault(Messages.DELETE_TOOL_DELETED, "&aO ArmorStand foi excluído, mas pode ser restaurado usando o comando restore. Ele será excluído completamente quando o servidor reiniciar");
        yml.addDefault(Messages.MOVE_TOOL_NAME, "&eMover");
        yml.addDefault(Messages.MOVE_TOOL_LORE, "&7Move o ArmorStand\n&7para onde você quiser\n\n&7Recomendado: use o comando\n&7/as move em vez disso\n\n&eClique para mover");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY_MOVING, "&cVocê já está na sessão de movimento");
        yml.addDefault(Messages.MOVE_TOOL_START_MESSAGE, "&aQuebre o bloco onde você quer que o ArmorStand fique");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY, "&cVocê já está na sessão de movimento");
        yml.addDefault(Messages.MOVE_TOOL_START, "&aQuebre o bloco onde você quer que o ArmorStand fique");
        yml.addDefault(Messages.TELEPORT_TOOL_NAME, "&eTeleportar");
        yml.addDefault(Messages.TELEPORT_TOOL_LORE, "&7Teleporta você para\n&7a localização do ArmorStand\n\n&eClique para se teleportar");
        yml.addDefault(Messages.PREVIEW_ACCEPTED, "&aEssa posição foi aceita para este ArmorStand.");
        yml.addDefault(Messages.PREVIEW_DENIED, "&aNova posição rejeitada com sucesso.");

        yml.options().copyDefaults(true);
        save();
    }
}